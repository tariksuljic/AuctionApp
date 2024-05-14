import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";

import { FormContainer } from "src/components";
import { productDetailsFormFields } from "src/forms/fields";
import { getCategoriesWithSubcategories } from "src/services";
import { BUTTON_LABELS, ROUTE_PATHS } from "src/constants";

import "./style.scss";

const ProductDetailsForm = ({ formData, setFormData }) => {
    const [categories, setCategories] = useState([]);
    const [subcategories, setSubcategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState(null);
    const [error, setError] = useState({});

    const navigate = useNavigate();

    const methods = useForm({
        mode: "onBlur",
        defaultValues: formData
    });

    const onSubmit = (data) => {
        setFormData(data);
        
        navigate("#prices");
    };

    const onCancel = () => {
        navigate(ROUTE_PATHS.MY_ACCOUNT);
    }

    const getCategories = () => {
        getCategoriesWithSubcategories()
        .then((categoriesData) => {
            const formattedCategories = categoriesData.map(category => ({
                value: category.id,
                label: category.name,
                subCategories: category.subCategories
            }));

            setCategories(formattedCategories);

            // autoselect category if already in form data
            if (formData.category) {
                setSelectedCategory(formData.category);
            }
        }).catch(err => {
            setError(err.message);
        });
    };

    useEffect(() => {
        getCategories();
    }, []);

    useEffect(() => {
        if (selectedCategory) {
            const category = categories.find(cat => cat.value === selectedCategory);

            if (category.subCategories) {
                const formattedSubcategories = category.subCategories.map(subcategory => ({
                    value: subcategory.id,
                    label: subcategory.name
                }));

                setSubcategories(formattedSubcategories);
            }
        }
    }, [selectedCategory, categories]);

    return (
        <div className="details-form form">
            <div className="form-header">
                <h5> ADD ITEM </h5>
            </div>
            <div className="form-fields">
                <FormContainer 
                    formFields={ productDetailsFormFields(categories, subcategories, setSelectedCategory) }
                    onSubmit={ methods.handleSubmit(onSubmit) }
                    onCancel={ onCancel }
                    buttonLabel={ BUTTON_LABELS.NEXT }
                    cancelLabel={ BUTTON_LABELS.CANCEL }
                    methods={ methods }
                />
            </div>
        </div>
    );
}

export default ProductDetailsForm;
