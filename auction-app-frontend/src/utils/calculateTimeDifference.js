import moment from "moment";

import { AUCTION_STATUS } from "src/constants";

export const calculateTimeLeft = (endDate) => {
  const endDateMoment = moment(endDate);
  const now = moment();
  const difference = endDateMoment.diff(now, "milliseconds");
  let timeLeft = "";

  if (difference > 0) {
    const duration = moment.duration(difference);
    const monthsLeft = duration.months();

    duration.subtract(moment.duration(monthsLeft, "months")); // subtract months from duration to get days hours and minutes

    const daysLeft = duration.days();
    const hoursLeft = duration.hours();
    const minutesLeft = duration.minutes();

    if (monthsLeft > 0) {
      timeLeft += `${ monthsLeft } month${ monthsLeft !== 1 ? "s" : "" } `;
    }
    if (daysLeft > 0) {
      timeLeft += `${ daysLeft } day${ daysLeft !== 1 ? "s" : "" } `;
    }
    if (hoursLeft > 0 && daysLeft === 0) {
      timeLeft += `${ hoursLeft} hour${ hoursLeft !== 1 ? "s" : "" } `;
    }
    if (minutesLeft > 0 && daysLeft === 0 && hoursLeft === 0) {
      timeLeft += `${ minutesLeft } minute${ minutesLeft !== 1 ? "s" : "" }`;
    }
  } else {
    timeLeft = AUCTION_STATUS.EXPIRED;
  }

  return timeLeft.trim();
}
