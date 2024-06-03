import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

import { FormContainer, ButtonLoadingIndicator } from "src/components";

import { registerUser  } from "src/services";
import { ROUTE_PATHS, BUTTON_LABELS, SUCCESSFUL_REGISTRATION_MESSAGE } from "src/constants";
import { registerFormFields } from "src/forms/fields";

import "./style.scss";

const RegisterForm = () => {
  const navigate = useNavigate();

  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const methods = useForm({
    mode: "onBlur"
  });

  const onSubmit = (data) => {
    data = { ...data };

    setLoading(true);

    registerUser(data)
      .then(() => {
        setError(null);

        setLoading(false);
        navigate(ROUTE_PATHS.LOGIN, { state: { message: SUCCESSFUL_REGISTRATION_MESSAGE } });
      })
      .catch((error) => {
        setLoading(false);
        setError(error.response.data.message);
    });
  }

  return (
    <div className="register-form-container">
      <h5 className="form-title">REGISTER</h5>
      <div className="register-form">
        <FormContainer 
          formFields={ registerFormFields } 
          onSubmit={ methods.handleSubmit(onSubmit) }
          buttonLabel={ loading ? <ButtonLoadingIndicator /> : BUTTON_LABELS.REGISTER }
          methods={ methods }
          error= { error }
        />
        <div className="form-link body-bold">
          <span>Already have an account? </span>
          <Link to={ ROUTE_PATHS.LOGIN }>
            <span className="login">Login</span>
          </Link>
        </div>
      </div>
    </div>
  )
}

export default RegisterForm
