import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

import { FormContainer } from "src/components";

import { loginUser } from "src/services";
import { useUserName } from "src/store/UserNameContext";
import { loginFormFields } from "src/forms/fields";
import { ROUTE_PATHS, BUTTON_LABELS } from "src/constants";

import "./style.scss";

const LoginForm = () => {
  const navigate = useNavigate();
  const { setUserName } = useUserName();

  const [error, setError] = useState(null);

  const methods = useForm({
    mode: "onBlur" // validate on blur, when user moves to the next field
  });

  const onSubmit = (data) => {
    loginUser(data)
      .then((response) => {
        setUserName(response.name);

        localStorage.setItem("accessToken", response.accessToken);
        
        navigate(ROUTE_PATHS.HOME);
      }).catch((error) => {
        setError(error.response.data.message);
      });
  }

  const errorMessage = error ? `${error}. Please enter your credentials again.` : null;

  return (
    <div className="login-form-container">
        <h5 className="form-title">LOGIN</h5>
        <div className="login-form">
          <FormContainer 
            formFields={ loginFormFields } 
            onSubmit={ methods.handleSubmit(onSubmit) } 
            buttonLabel={ BUTTON_LABELS.LOGIN }
            methods={ methods }
            error={ errorMessage }
          />
        </div>
    </div>
  )
}

export default LoginForm
