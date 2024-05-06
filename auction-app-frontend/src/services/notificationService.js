import { getRequest } from "src/utils/httpUtils";

const getLatestNotification = (userId, productId) => {
    return getRequest(`/notifications/latest?userId=${userId}&productId=${productId}`);
};

export { getLatestNotification };
