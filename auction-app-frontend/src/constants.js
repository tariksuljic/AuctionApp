import { 
  seller, 
  profile, 
  settings, 
  bids, 
  sellerActive,
  profileActive,
  settingsActive,
  bidsActive,
  gavel,
  cart
} from "src/assets/icons";

export const BASE_URL = import.meta.env.VITE_BASE_URL;

export const WEB_SOCKET_BASE_URL = import.meta.env.VITE_WEB_SOCKET_BASE_URL;

export const STRIPE_PUBLIC_KEY = import.meta.env.VITE_STRIPE_PUBLIC_KEY;

export const NAV_ITEMS = [
  {
    label: "HOME",
    link: "/",
    key: "home",
  },
  {
    label: "SHOP",
    link: "/shop",
    key: "shop",
  },
  {
    label: "MY ACCOUNT",
    link: "/my-account",
    key: "my-account",
  },
];

export const ROUTES_MAP = {
  "/": "Home",
  "/shop": "Shop",
  "/my-account": "My Account",
  "/about-us": "About Us",
  "/privacy-policy": "Privacy Policy",
  "/terms-and-conditions": "Terms and Conditions",
  "/checkout": "Checkout",
  // will add more routes
};

export const ROUTE_PATHS = {
  HOME: "/",
  SHOP: "/shop",
  MY_ACCOUNT: "/my-account",
  ABOUT_US: "/about-us",
  PRIVACY_POLICY: "/privacy-policy",
  TERMS_AND_CONDITIONS: "/terms-and-conditions",
  PRODUCT: "/product",
  LOGIN: "/login",
  REGISTER: "/register",
  ADD_ITEM: "/add-item",
  CHECKOUT: "/checkout",
};

export const HIDE_BREADCRUMBS_ON_PATHS = [
  ROUTE_PATHS.HOME,
  ROUTE_PATHS.SHOP,
  ROUTE_PATHS.LOGIN,
  ROUTE_PATHS.REGISTER,
];

export const HIDE_NAV_OPTIONS_ON_PATHS = [
  ROUTE_PATHS.LOGIN,
  ROUTE_PATHS.REGISTER,
];

export const HOME_TABS = [
  { id: "newArrivals", label: "New Arrivals" },
  { id: "lastChance", label: "Last Chance" },
];

export const PRODUCT_DETAILS_TABS = [{ id: "details", label: "Details" }];

export const SEARCH_RESULTS = "Search results for"

export const SHOP_DEFAULT_PAGE_NUMBER = 9;

export const HOME_DEFAULT_PAGE_NUMBER = 8;

export const BIDS_DEFAULT_PAGE_NUMBER = 8;

export const BUTTON_LABELS = {
  EXPIRED: "Expired",
  REGISTER: "REGISTER",
  LOGIN: "LOGIN",
  PLACE_BID: "PLACE BID",
  ADD_ITEM: "ADD ITEM",
  CHANGE_PHOTO: "Change photo",
  ADD_ITEM: "ADD ITEM",
  CHANGE_PHOTO: "Change photo",
  SAVE_INFO: "SAVE INFO",
  BID: "BID",
  LOAD_MORE: "LOAD MORE",
  DEACTIVATE: "DEACTIVATE",
  CANCEL: "CANCEL",
  NEXT: "NEXT",
  BACK: "BACK",
  VIEW: "VIEW",
  PAY: "PAY",
};

export const CONNECTION_STATUSES = {
  CONNECTED: "connected",
  DISCONNECTED: "disconnected",
};

export const USER_TYPES = {
  ADMIN: "ADMIN",
  USER: "USER"
}

export const AUCTION_STATUS = {
  EXPIRED: "Expired",
};

export const BUTTON_VARIANTS = {
  OUTLINED: "outlined",
  FILLED: "filled"
}

export const MY_ACCOUNT_TABS = [
  { id: "profile", label: "Profile", icon: profile, activeIcon: profileActive},
  { id: "seller", label: "Seller", icon: seller, activeIcon: sellerActive},
  { id: "bids", label: "Bids", icon: bids, activeIcon: bidsActive},
  { id: "settings", label: "Settings", icon: settings, activeIcon: settingsActive},
];

export const HEADERS = [
  { id: "item", text: "Item" },
  { id: "name", text: "Name"},
  { id: "timeLeft", text: "Time Left" },
  { id: "yourPrice", text: "Your Price" },
  { id: "noBids", text: "No. Bids" },
  { id: "highestBid", text: "Highest Bid" },
  { id: "actions", text: "" }
];

export const SELLER_TABS = [
  { id: "active", label: "Active" },
  { id: "sold", label: "Sold" },
];

export const POLICY_AND_COMMUNITY = [
  { id: "email", label: "Email" },
  { id: "pushNotifications", label: "Push Notifications" },
  { id: "smsNotifications", label: "SMS" },
];

export const MY_ACCOUNT_TABS_MAP = {
  PROFILE: "profile",
  SELLER: "seller",
  BIDS: "bids",
  SETTINGS: "settings",
};

export const EMPTY_TABS = [
  { 
  id: "bids", 
  icon: gavel,
  text: "You don’t have any bids and there are so many cool products available for sale",
  boldText: "START BIDDING" 
  },
  {
    id: "seller",
    icon: cart,
    text: "You do not have any scheduled items for sale",
    boldText: "START SELLING"
  }
];

export const HIGHEST_BID_MESSAGE = "Congratulations! You are the highest bidder.";
export const LOWER_BID_MESSAGE = "There are higher bids than yours. You could give a second try.";

export const ADD_ITEM_FORMS = [
  { id: "details", label: "Details" },
  { id: "prices", label: "Prices" },
  { id: "shipping", label: "Shipping" },
];

export const ADD_ITEM_FORMS_MAP = {
  DETAILS: "details",
  PRICES: "prices",
  SHIPPING: "shipping",
};

export const SUCCESSFUL_REGISTRATION_MESSAGE = "Registration successful, please login.";

export const FILE_TYPES = ["JPG", "PNG", "GIF"];

export const PRODUCT_STATUS = {
  ACTIVE: "ACTIVE",
  SOLD: "SOLD",
  INACTIVE: "INACTIVE",
};

export const FIELD_TYPES = {
  TEXT: "text",
  TEXTAREA: "textarea",
  SELECT: "select",
  FILE: "file",
  NUMBER: "number",
  DATE: "date",
};

export const CHECKOUT_STEPS = {
  ADDRESS: "address",
  PAYMENT: "payment",
};

export const SHOP_PAGE_SORTING = [
  { id: "default", value: "Default Sorting", label: "Default Sorting", direction: "ASC", criteria: "name" },
  { id: "priceLowToHigh", value: "Price: Low to High", label: "Price: Low to High", direction: "ASC", criteria: "startPrice"},
  { id: "priceHighToLow", value: "Price: High to Low", label: "Price: High to Low", direction: "DESC", criteria: "startPrice" },
  { id: "added", value: "Added: New to Old", label: "Added: New to Old", direction: "DESC", criteria: "startDate" },
  { id: "timeLeft", value: "Last Chance", label: "Last Chance", direction: "ASC", criteria: "endDate" },
];
