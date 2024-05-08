import { Button, Checkbox } from "src/components";

import { BUTTON_LABELS, POLICY_AND_COMMUNITY } from "src/constants";

import "./style.scss"

const SettingsTab = () => {
  return (
    <div className="settings-tab-container">
        <div className="box-container body-semibold">
            <div className="box top-left">
                <span className="box-heading">Policy and Community</span>
                <span className="box-description">Receive updates on bids and seller's offers. Stay informed through:</span>
                <div className="checkbox-container body-regular">
                    { POLICY_AND_COMMUNITY.map((item) => (
                        <Checkbox key={ item.id } label={ item.label } />
                    )) }
                </div>
            </div>
            <div className="box top-right">
                <span className="box-heading">Contact Information</span>
                <span className="box-description">This information can be edited on your profile.</span>
                <span className="box-heading">Email <span className="box-information body-semibold">johndoe@gmail.com</span></span>
                <span className="box-heading">Phone <span className="box-information body-semibold">555 555 555</span></span>
            </div>
            <div className="box bottom-left">
                <span className="box-heading">Account Information</span>
                <span className="box-description">Do you want to deactivate account?</span>
                <Button label={ BUTTON_LABELS.DEACTIVATE } disabled={ true }/>
            </div>
        </div>
    </div>
  )
}

export default SettingsTab;
