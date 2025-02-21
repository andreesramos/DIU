import React from "react";
import "../styles/ProgressBar.css";

const ProgressBar = ({ value, max }) => {
  const percentage = max > 0 ? Math.round((value / max) * 100) : 0;

  return (
    <div className="progress-container">
      <progress className="progress-bar" value={value} max={max}></progress>
      {percentage > 100 ? (
        <span className="progress-label">
            100%
        </span>
      ) : (
        <span className="progress-label">
            {percentage}%
        </span>
      )}
    </div>
  );
};

export default ProgressBar;
