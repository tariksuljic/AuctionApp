import { createContext, useContext, useEffect, useRef, useCallback, useState } from "react";

import { CONNECTION_STATUSES, WEB_SOCKET_BASE_URL } from "src/constants";

import { useUser } from "src/store/UserContext";

const WebSocketContext = createContext(null);

export const useWebSocket = () => useContext(WebSocketContext);

export const WebSocketProvider = ({ children }) => {
  const [connectionStatus, setConnectionStatus] = useState(CONNECTION_STATUSES.DISCONNECTED);
  const [webSocketError, setWebSocketError] = useState(null);

  const { userId } = useUser();

  const ws = useRef(null);
  const listeners = useRef([]);

  const broadcastMessage = useCallback((message) => {
    listeners.current.forEach(listener => listener(message));
  }, []);

  useEffect(() => {
    if (!userId) return undefined;

    ws.current = new WebSocket(`${ WEB_SOCKET_BASE_URL }?userId=${ userId }`);

    ws.current.onopen = () => setConnectionStatus(CONNECTION_STATUSES.CONNECTED);

    ws.current.onmessage = (event) => {
      broadcastMessage(event.data);
    };

    ws.current.onclose = () => setConnectionStatus(CONNECTION_STATUSES.DISCONNECTED);

    ws.current.onerror = (error) => {
      setConnectionStatus(CONNECTION_STATUSES.DISCONNECTED);
      setWebSocketError(error.message);
    };

    return () => {
      if (ws.current) {
        ws.current.close();
        ws.current = null;
      }
    };
  }, [userId, broadcastMessage]);

  const addMessageListener = useCallback((listener) => {
    listeners.current.push(listener);
  }, []);

  const removeMessageListener = useCallback((listener) => {
    listeners.current = listeners.current.filter(l => l !== listener);
  }, []);

  return (
    <WebSocketContext.Provider value={{ addMessageListener, removeMessageListener }}>
      {children}
    </WebSocketContext.Provider>
  );
};
