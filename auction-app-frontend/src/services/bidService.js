import { postRequest } from "src/utils/httpUtils";

const placeBid = (data) => {
    return postRequest("/bids/place-bid", data);
}

export { placeBid };
