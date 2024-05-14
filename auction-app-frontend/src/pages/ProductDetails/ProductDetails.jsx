import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";

import { Tabs, LoadingComponent, ErrorComponent, FormContainer, Notifications } from "src/components";

import { useBreadcrumb } from "src/store/BreadcrumbContext";
import { useUser } from "src/store/UserContext";
import { getProduct, getBidData } from "src/services";
import { placeBid } from "src/services/bidService";
import { calculateTimeLeft } from "src/utils/calculateTimeDifference";

import { 
  PRODUCT_DETAILS_TABS, 
  BUTTON_LABELS, BUTTON_VARIANTS, 
  USER_TYPES,
  AUCTION_STATUS
} from "src/constants";
import { placeBidsFormFields } from "src/forms/fields";
import { go } from "src/assets/icons";

import "./style.scss";

const ProductDetails = () => {
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

  const fetchNewProductDetails = () => {
    setBidDataLoading(true);

    getBidData(id)
      .then((bidData) => {
        // append bid data to product
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
      bidTime: new Date().toISOString(), // format date to match the backend
      productId: product.id,
      userId: userId
    };

    placeBid(bidDetails)
      .then(() => {
        setError(null);
      })
      .catch((error) => {
        setError(error.response.data.message);
      });

    methods.reset();
    fetchNewProductDetails();
  };

  if (loading) return <LoadingComponent />;
  if (error) return <ErrorComponent message={ error } />;

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
          { (AUCTION_STATUS.EXPIRED !== timeLeft && USER_TYPES.USER === userType && userId !== product.userId) && (
            <div className="place-bid-form">
              <FormContainer 
                formFields={ additionalPlaceBidsFormFields } 
                onSubmit={ methods.handleSubmit(onSubmit) }
                buttonLabel={ BUTTON_LABELS.PLACE_BID }
                buttonVariant={ BUTTON_VARIANTS.OUTLINED }
                buttonIcon={ go }
                methods={ methods }
                error= { error }
              />
            </div>
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
