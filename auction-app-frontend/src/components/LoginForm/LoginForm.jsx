import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useForm } from "react-hook-form";

import { FormContainer, ButtonLoadingIndicator } from "src/components";

import { loginUser } from "src/services";
import { useUser } from "src/store/UserContext";
import { loginFormFields } from "src/forms/fields";
import { ROUTE_PATHS, BUTTON_LABELS } from "src/constants";

import "./style.scss";

const LoginForm = () => {
  const navigate = useNavigate();
  const { setUserName } = useUser();

  const location = useLocation();
  const { from } = location.state || { from: { pathname: "/" } };

  const [message, setMessage] = useState(location.state?.message || null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const methods = useForm({
    mode: "onBlur" // validate on blur, when user moves to the next field
  });

  useEffect(() => {
    if (location.state?.message) {
      setMessage(location.state.message);
    }
  }, [location]);

  const onSubmit = (data) => {
    setLoading(true);

    loginUser(data)
      .then((response) => {
        setUserName(response.name);

        localStorage.setItem("accessToken", response.accessToken);
        
        if (from.pathname) { // if user was redirected to login page, redirect back to the previous page
          navigate(from.pathname);
          return;
        } else {
          navigate(ROUTE_PATHS.HOME);
        }

        setLoading(false);
      }).catch((error) => {
        setError(error.response.data.message);
        setLoading(false);
      });
  }

  const errorMessage = error ? `${ error }. Please enter your credentials again.` : null;

  return (
    <div className="login-form-container">
        <h5 className="form-title">LOGIN</h5>
        { message && <div className="alert-success body-regular">{ message }</div> }
        <div className="login-form">
          <FormContainer 
            formFields={ loginFormFields } 
            onSubmit={ methods.handleSubmit(onSubmit) } 
            buttonLabel={ loading ? <ButtonLoadingIndicator /> : BUTTON_LABELS.LOGIN }
            methods={ methods }
            error={ errorMessage }
          />
        </div>
    </div>
  )
}

export default LoginForm;
