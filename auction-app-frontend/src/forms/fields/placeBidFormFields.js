import { rules } from "src/forms/rules";

export const placeBidsFormFields = (startPrice, highestBidAmount) => {
    const minBid = highestBidAmount > startPrice ? highestBidAmount + 0.10 : startPrice;

    return [
        {
            type: "number",
            name: "bidAmount",
            step: "0.01",
            rules: {
                ...rules.required("Bid amount"),
                ...rules.minValue(minBid, "Bid amount")
            }
        }
    ];
};
