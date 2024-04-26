import { FormProvider } from 'react-hook-form';

import { InputField, Button } from 'src/components';

const FormContainer = ({ formFields, onSubmit, buttonLabel, methods, error }) => {
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
            />
        )) }
        { error && 
          <div className="error-message-body body-semibold">
            { error }
          </div> 
        }
        <Button type="submit" variant="filled" label={ buttonLabel } />
      </form>
    </FormProvider>
  );
}

export default FormContainer;
