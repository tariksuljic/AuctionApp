import { useForm } from "react-hook-form";

import { FormContainer } from "src/components";
import { locationShippingFormFields } from "src/forms/fields";
import { BUTTON_LABELS } from "src/constants";
import { useUser } from "src/store/UserContext";

import "./style.scss";

const CheckoutAddressForm = ({ onAddressFormSubmit, initialData }) => {
    const { email } = useUser();

    const methods = useForm({
        mode: "onBlur",
        defaultValues: {
            ...initialData,
            email: email
        }
    });

    const onSubmit = () => {
        const formData = methods.getValues();

        onAddressFormSubmit(formData);
    }

    return (
        <div className="address-form-container">
            <FormContainer 
                formFields={ locationShippingFormFields }
                onSubmit={ methods.handleSubmit(onSubmit) }
                buttonLabel={ BUTTON_LABELS.NEXT }
                methods={ methods }
            />
        </div>
    )
}

export default CheckoutAddressForm
