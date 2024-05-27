import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";

import { FormContainer, LoadingComponent, ErrorComponent } from "src/components";
import { locationShippingFormFields, cardInformationFields } from "src/forms/fields";
import { BUTTON_LABELS, ROUTE_PATHS } from "src/constants";
import { getUser } from "src/services";
import { useUser } from "src/store/UserContext";

import "./style.scss";

const LocationForm = ({ formData, setFormData, handleFinalSubmit }) => {
    const navigate = useNavigate();
    const { userId, email } = useUser();

    const [originalPaymentInfo, setOriginalPaymentInfo] = useState(formData);
    const [showCardInfo, setShowCardInfo] = useState(false);
    const [paymentInfo, setPaymentInfo] = useState({});
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const methods = useForm({
        mode: "onBlur", 
        defaultValues: formData
    });

    const fetchPaymentInfo = () => {
        if (!userId) return;

        setLoading(true);

        getUser(userId)
            .then((response) => {
                if (!response.paymentInfoEntity || !response.paymentInfoEntity.creditCardEntity) {
                    setPaymentInfo({});

                    methods.reset({ ...formData });
                    return;
                }

                const { expirationDate, ...rest } = response.paymentInfoEntity.creditCardEntity;
                const [year, month, day] = expirationDate.split('-');

                const expirationMonth = month;
                const expirationYear = year.substring(2);

                const updatedPaymentInfo = {
                    ...response.paymentInfoEntity,
                    ...rest,
                    expirationMonth,
                    expirationYear,
                    email
                };

                setPaymentInfo(updatedPaymentInfo);
                setOriginalPaymentInfo(updatedPaymentInfo); // for later use, to compare if shipping data has changed before submitting
                setFormData({ ...formData, ...updatedPaymentInfo });
                methods.reset({ ...methods.getValues(), ...updatedPaymentInfo });
            }).catch((error) => {
                if (error.response && error.response.status === 500) {
                    methods.reset({ ...methods.getValues() }); // reset form if user has no payment info
                } else {
                    setError(error.message);
                }
            }).finally(() => {
                setLoading(false);
            });
    };

    useEffect(() => {
        fetchPaymentInfo();
    }, [userId]);

    const hasDataChanged = () => {
        const currentData = methods.getValues();
        // compare without CVV
        const currentDataWithoutCVV = {...currentData};
        const originalDataWithoutCVV = {...originalPaymentInfo};
        
        delete currentDataWithoutCVV.cvv;
        delete originalDataWithoutCVV.cvv;

        return JSON.stringify(currentDataWithoutCVV) !== JSON.stringify(originalDataWithoutCVV);
    };

    const onSubmit = () => {
        const data = methods.getValues();

        if (!showCardInfo) {
            setFormData({...formData, ...data, dataChanged: hasDataChanged()});
            setShowCardInfo(true);
        } else {
            setFormData({...formData, ...data, dataChanged: hasDataChanged()});
            handleFinalSubmit();
        }
    };

    const onCancel = () => {
        navigate(ROUTE_PATHS.MY_ACCOUNT);
    };

    const onBack = () => {
        if (showCardInfo) {
            setShowCardInfo(false);
        } else {
            navigate("#prices");
        }
    };

    const combinedFormFields = showCardInfo ? 
        [...locationShippingFormFields, ...cardInformationFields] : locationShippingFormFields;

    if (loading) return <LoadingComponent />;
    if (error) return <ErrorComponent message={error} />;

    return (
        <div className="location-form form">
            <div className="form-header">
                <h5>LOCATION & SHIPPING</h5>
            </div>
            <div className="form-fields">
                <FormContainer 
                    formFields={ combinedFormFields }
                    onSubmit={ methods.handleSubmit(onSubmit) }
                    onCancel={ onCancel }
                    onBack={ onBack }
                    buttonLabel={ BUTTON_LABELS.NEXT }
                    cancelLabel={ BUTTON_LABELS.CANCEL }
                    backLabel={ BUTTON_LABELS.BACK }
                    methods={ methods }
                />
            </div>
        </div>
    );
};

export default LocationForm;
