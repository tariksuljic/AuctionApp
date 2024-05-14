import { FormProvider } from "react-hook-form";
import { useState } from "react";

import { InputField, TextAreaField, SelectField, CustomFileUploader, Button } from "src/components";
import { BUTTON_VARIANTS, FIELD_TYPES } from "src/constants";

const FormContainer = ({
  formFields,
  onSubmit,
  buttonLabel,
  methods,
  error,
  buttonVariant = BUTTON_VARIANTS.FILLED,
  buttonIcon,
  children,
  onCancel,
  cancelLabel,
  onBack,
  backLabel
}) => {
  const [files, setFiles] = useState([]);

  return (
    <FormProvider {...methods}>

      <form onSubmit={ methods.handleSubmit(onSubmit) }>
        { children ? children : formFields.map((field) => {
          switch (field.type) {
            case FIELD_TYPES.TEXTAREA:
              return (
                <TextAreaField
                  key={ field.name }
                  name={ field.name }
                  label={ field.label}
                  rules={ field.rules }
                  className={ field.specialClass }
                  placeholder={ field.placeholder }
                />
              );
            case FIELD_TYPES.SELECT:
              return (
                <SelectField
                  key={ field.name }
                  name={ field.name }
                  options={ field.options }
                  label={ field.label }
                  rules={ field.rules }
                  onSelectChange={ field.onSelectChange }
                  className={ field.specialClass }
                />
              );
            case FIELD_TYPES.FILE:
              return (
                <CustomFileUploader
                  key={ field.name }
                  name={ field.name }
                  setFiles={ setFiles }
                  setValue={ methods.setValue }
                  files={ files }
                  rules={ field.rules }
                />
              );
            default:
              return (
                <InputField
                  key={ field.name }
                  name={ field.name }
                  label={ field.label }
                  type={ field.type }
                  rules={ field.rules }
                  step={ field.type === FIELD_TYPES.NUMBER ? field.step : undefined }
                  className={ field.specialClass }
                  placeholder={ field.placeholder }
                />
              );
           }
         }) }
        { error &&
          <div className="error-message-body body-semibold">
            { error }
          </div>
         }
        <div className="button-container">
          { onCancel && <Button type="button" label={ cancelLabel } onButtonClick={ onCancel } /> }
          <div className="btn-navigation">
            { onBack && <Button type="button" label={ backLabel } onButtonClick={ onBack } /> }
            <Button type="submit" variant={ buttonVariant } label={ buttonLabel } iconSrc={ buttonIcon } />
          </div>
        </div>
      </form>
    </FormProvider>
  );
 };

export default FormContainer;
