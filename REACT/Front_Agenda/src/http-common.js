import axios from "axios";

export default axios.create({
  //baseURL: "http://tutorials2025arn.us-east-1.elasticbeanstalk.com/api/v1",
  baseURL: "http://localhost:82/api/v1",
  headers: {
    "Content-type": "application/json"
  }
});