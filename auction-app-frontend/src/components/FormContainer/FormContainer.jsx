import { FormProvider } from "react-hook-form";

import { InputField, Button } from "src/components";
import { BUTTON_VARIANTS } from "src/constants";

const FormContainer = ({ formFields, onSubmit, buttonLabel, methods, error, buttonVariant = BUTTON_VARIANTS.FILLED, buttonIcon }) => {
  return (
    <FormProvider { ...methods }>
      <form onSubmit={ methods.handleSubmit(onSubmit) }>
        { formFields.map(field => (
            <InputField
                key={ field.name }
                name={ field.name }
                label={ field.label }
                type={ field.type }
                rules={ field.rules }
                step={ field.step }
            />
        )) }
        { error && 
          <div className="error-message-body body-semibold">
            { error }
          </div> 
        }
        <Button type="submit" variant={ buttonVariant } label={ buttonLabel } iconSrc= { buttonIcon }/>
      </form>
    </FormProvider>
  );
}

export default FormContainer;
