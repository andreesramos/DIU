import React, { useEffect, useState } from "react";
import ProductDataService from "../services/product.service";
import "../styles/ProgressBar.css";

const ProgressBar = ({ value, max }) => {
  const percentage = max > 0 ? Math.round((value / max) * 100) : 0;
  return (
    <div className="progress-container">
      <progress className="progress-bar" value={value} max={max}></progress>
      <span className="progress-label">{percentage}%</span>
    </div>
  );
};

const ProgressBarContainer = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    ProductDataService.findAll()
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => {
        console.error("Error fetching products:", error);
      });
  }, []);

  return <ProgressBar value={products.length} max={50} />;
};

export default ProgressBarContainer;