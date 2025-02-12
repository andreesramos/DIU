import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8099/api/v1/agenda",
  headers: {
    "Content-type": "application/json"
  }
});