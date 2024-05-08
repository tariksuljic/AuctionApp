import moment from "moment";

export const calculateTimeLeft = (endDate) => {
  const endDateMoment = moment(endDate);
  const now = moment();
  const difference = endDateMoment.diff(now, "milliseconds");
  let timeLeft = "";

  if (difference > 0) {
    const duration = moment.duration(difference);
    const daysLeft = duration.days();
    const hoursLeft = duration.hours();
    const minutesLeft = duration.minutes();

    if (daysLeft > 0) {
      timeLeft += `${ daysLeft } day${ daysLeft !== 1 ? "s" : "" }`;
    } else if (hoursLeft > 0) {
      timeLeft += `${ hoursLeft } hour${ hoursLeft !== 1 ? "s" : "" }`;
    } else if (minutesLeft > 0) {
      timeLeft += `${ minutesLeft } minute${ minutesLeft !== 1 ? "s" : "" }`;
    } else {
      timeLeft += "Less than a minute";
    }
  } else {
    timeLeft += "Expired";
  }

  return timeLeft;
}
