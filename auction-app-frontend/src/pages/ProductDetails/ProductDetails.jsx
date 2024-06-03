import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams, useNavigate } from "react-router-dom";
import Modal from "react-modal";

import { Tabs, LoadingComponent, ErrorComponent, FormContainer, Notifications, Button, CheckoutComponent } from "src/components";

import { useBreadcrumb } from "src/store/BreadcrumbContext";
import { useUser } from "src/store/UserContext";
import { getProduct, getBidData } from "src/services";
import { placeBid } from "src/services/bidService";
import { calculateTimeLeft } from "src/utils/calculateTimeDifference";
import { close } from "src/assets/icons";

import { 
  PRODUCT_DETAILS_TABS, 
  BUTTON_LABELS, 
  BUTTON_VARIANTS, 
  USER_TYPES,
  PRODUCT_STATUS,
  ROUTE_PATHS
} from "src/constants";
import { placeBidsFormFields } from "src/forms/fields";
import { go } from "src/assets/icons";

import "./style.scss";

const ProductDetails = () => {
  const navigate = useNavigate();

  const [activeTab, setActiveTab] = useState(PRODUCT_DETAILS_TABS[0].id);
  const [product, setProduct] = useState(null);
  const [productImages, setProductImages] = useState([]);
  const [mainImage, setMainImage] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [timeLeft, setTimeLeft] = useState(0);
  const [additionalPlaceBidsFormFields, setAdditionalPlaceBidsFormFields] = useState([]);
  const [bidDataLoading, setBidDataLoading] = useState(false);
  const [bidDataError, setBidDataError] = useState(null);
  const [isCheckoutModalOpen, setIsCheckoutModalOpen] = useState(false);

  const { userType, userId } = useUser(); 

  const { id } = useParams();

  const { setTitle } = useBreadcrumb();

  const methods = useForm({
    mode: "onBlur"
  });

  const fetchInitialData = () => {
    setLoading(true);

    setTimeout(() => {
      getProduct(id)
        .then((productDetail) => {
          setProduct(productDetail);
          setMainImage(productDetail.productImages[0].imageUrl);
          setProductImages(productDetail.productImages.slice(1));
        })
        .catch((err) => {
          setError("Failed to fetch initial data", err.message);
        })
        .finally(() => {
          setLoading(false);
        });
    }, 500);
  };

  useEffect(() => {
    fetchInitialData();
  }, [id]);

  useEffect(() => {
    if (product) {
      setTitle(`${ product.name }`);
      setAdditionalPlaceBidsFormFields(placeBidsFormFields(product.startPrice, product.highestBid));
      
      setTimeLeft(calculateTimeLeft(product.endDate));
    }
  }, [product, setTitle]);

  const setActiveTabHandler = (tabId) => {
    setActiveTab(tabId);
  };

  const handleImageClick = (clickedImage) => {
    // find current main image in the product"s image list
    const previousMainImage = product.productImages.find(
      (img) => img.imageUrl === mainImage
    );

    // set the clicked image as the new one
    setMainImage(clickedImage.imageUrl);

    const updatedImagesList = productImages
      .filter((img) => img.imageUrl !== clickedImage.imageUrl) // remove the clicked image
      .concat(previousMainImage); // add the previous main image back

    setProductImages(updatedImagesList);
  };

  const onSubmit = (data) => {
    const bidDetails = {
      bidAmount: data.bidAmount,
      bidTime: new Date().toISOString(),
      productId: product.id,
      userId: userId
    };

    placeBid(bidDetails)
      .then((newBidResponse) => {
        setError(null);
        methods.reset();
        // update the product details with the new bid data
        setProduct(prevProduct => ({
          ...prevProduct,
          highestBid: newBidResponse.highestBid || prevProduct.highestBid,
          bidsCount: newBidResponse.bidsCount
        }));
        
        fetchNewProductDetails();
      })
      .catch((error) => {
        setError(error.response.data.message);
      });
  };
  
  const fetchNewProductDetails = () => {
    setBidDataLoading(true);
  
    getBidData(id)
      .then((bidData) => {
        setProduct((prevProduct) => ({
          ...prevProduct,
          highestBid: bidData.highestBid,
          bidsCount: bidData.bidsCount
        }));
      })
      .catch((error) => {
        setBidDataError(error.message);
      })
      .finally(() => {
        setBidDataLoading(false);
      });
  };

  const checkout = () => {
    setIsCheckoutModalOpen(true);
  };

  const closeCheckoutModal = () => {
    setIsCheckoutModalOpen(false);
  };  
  
  if (loading) return <LoadingComponent />;
  if (error) return <ErrorComponent message={ error } />;

  const isAuctionActive = PRODUCT_STATUS.ACTIVE === product?.status;
  const userIsHighestBidder = userId === product?.highestBidderId;
  const userIsSeller = userId === product?.userId;
  const canBid = USER_TYPES.USER === userType && userId && !userIsSeller && isAuctionActive;

  return (
    <>
      <Notifications productId={ id } fetchProductOnUpdate={ fetchNewProductDetails }/>
      <div className="product-details-container">
        <div className="product-details-images">
          <div className="main-image-container">
            <img src={ mainImage } alt="Product" />
            <div className="other-images-container">
              { productImages.map((image, index) => (
                <div
                  key={ `${ image.imageUrl }-${ index }` }
                  className="image-container"
                >
                  <img
                    src={ image.imageUrl }
                    alt={ `Product ${ index + 1 }` }
                    onClick={ () => handleImageClick(image) }
                  />
                </div>
              )) }
            </div>
          </div>
        </div>
        <div className="product-details">
          <div className="product-name">
            <span className="body-semibold">{ product.name }</span>
            <span className="body-regular">
              Starts from <span className="price">${ product.startPrice }</span>
            </span>
          </div>
          <div className="product-bid-details">
            <div className="product-bid-details-items">
              <div className="product-bid-details-item">
                <span className="item-key">Highest Bid: </span>
                <span className="item-value">
                  { bidDataLoading ? (
                      <span className="body-regular">Loading...</span>
                    ) : (
                      <span className="body-bold">
                        { product.highestBid === null ? "0" : `$${ product.highestBid }` }
                      </span>
                    ) }
                </span>
              </div>
              <div className="product-bid-details-item">
                <span className="item-key">Number of bids: </span>
                <span className="item-value">
                  { bidDataLoading ? (
                      <span className="body-regular">Loading...</span>
                    ) : (
                      <span className="body-bold">
                        { product.bidsCount }
                      </span>
                    ) }
                </span>
              </div>
              <div className="product-bid-details-item">
                <span className="item-key">Time left: </span>
                <span className="item-value">{ timeLeft }</span>
              </div>
            </div>
          </div>
          { canBid ? (
            <div className="place-bid-form">
              <FormContainer 
                formFields={ additionalPlaceBidsFormFields } 
                onSubmit={ methods.handleSubmit(onSubmit) } 
                buttonLabel={ BUTTON_LABELS.PLACE_BID } 
                buttonVariant={ BUTTON_VARIANTS.OUTLINED } 
                buttonIcon={ go } 
                methods={ methods } 
                error={ error }
              />
            </div>
            ) : !isAuctionActive && userIsHighestBidder ? (
              <div className="payment-info">
                <Button 
                  onButtonClick={ checkout } 
                  label={ BUTTON_LABELS.PAY } 
                  variant = { BUTTON_VARIANTS.FILLED }
                />
              </div>
            ) : !isAuctionActive ? (
              <span className="no-active-auction body-bold">Auction has ended</span>
            ) : userId ? (
              // just a placeholder for now
              <span className="no-active-auction body-bold">You can't bid on your own item</span>
            ) : null 
            }
            { isCheckoutModalOpen && (
              <Modal 
                isOpen={ isCheckoutModalOpen } 
                onRequestClose={ closeCheckoutModal } 
                contentLabel="Checkout"
                className="checkout-modal"
                overlayClassName="modal-overlay"
                appElement={ document.getElementById('root') }
              >
                <img src = { close } alt="Close" className="close-icon" onClick={ closeCheckoutModal } />
                <CheckoutComponent product={ product } closeCheckout={ closeCheckoutModal } />
              </Modal>
            ) }
          <div className="product-information">
            <Tabs
              tabs={ PRODUCT_DETAILS_TABS }
              activeTab={ activeTab }
              onTabClick={ setActiveTabHandler }
            />
            { activeTab === "details" && (
              <div className="product-description">
                <p>{ product.description }</p>
              </div>
            ) }
          </div>
        </div>
      </div>
    </>
  );
};

export default ProductDetails;
