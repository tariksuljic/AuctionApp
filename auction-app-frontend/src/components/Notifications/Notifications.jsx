import { useEffect, useState } from "react";

import { LoadingComponent, ErrorComponent } from "src/components";

import { useUser } from "src/store/UserContext";
import { useWebSocket } from "src/store/WebSocketContext";
import { getLatestNotification } from "src/services/notificationService";
import { HIGHEST_BID_MESSAGE, LOWER_BID_MESSAGE } from "src/constants";

import "./style.scss";

const Notifications = ({ productId, fetchProductOnUpdate }) => {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [notification, setNotification] = useState(null);

  const { userId } = useUser();
  const { addMessageListener, removeMessageListener } = useWebSocket();

  const fetchLatestNotification = async () => {
    setLoading(true);

    getLatestNotification(userId, productId)
      .then(notification => {
        setNotification(notification);
      })
      .catch(error => {
        if (error.response && error.response.status === 404) {
          setNotification(null); // if no notification found set message to null
        } else { 
          setError(error.message);
        }
      })
      .finally(() => {
        setLoading(false);
      });
  };

  useEffect(() => {
    if (!userId || !productId) {
      setLoading(false);

      return;
    }

    fetchLatestNotification();
  }, [userId, productId]);

  
  const getNotificationClass = () => {
    switch (notification?.notificationType) {
      case "HIGHEST_BID":
        return "primary";
      case "LOWER_BID":
        return "accent";
      default:
        return ""; // no type matched
    }
  };

  useEffect(() => {
    const handleNewMessage = (rawMessage) => {
      if (typeof rawMessage === "string") {
        const notificationType = rawMessage.includes(HIGHEST_BID_MESSAGE) ? "HIGHEST_BID" : "LOWER_BID";

        const newNotification = {
          messageContent: rawMessage,
          notificationType: notificationType,
        };
        setNotification(newNotification);
        
        fetchProductOnUpdate(); // fetch product details when a new notification is received
      }
    };
    addMessageListener(handleNewMessage);
  
    return () => {
      removeMessageListener(handleNewMessage);
    };
  }, [addMessageListener, removeMessageListener, productId]);

  if (loading) return <LoadingComponent />;
  if (error) return <ErrorComponent message={error} />;  

  return (
    <>
      { notification?.messageContent && (
        <div className={`notification ${ getNotificationClass() } body-bold`}>
          { notification.messageContent }
        </div>
      ) }
    </>
  );
};

export default Notifications;
