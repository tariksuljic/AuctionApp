const ErrorComponent = ({ message }) => (
  <div className="error">
    <span className="body-semibold">{ message || "An error occurred." }</span>
  </div>
);

export default ErrorComponent;
