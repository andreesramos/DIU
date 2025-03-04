import axios from "axios";

export default axios.create({
  //baseURL: "http://localhost:82/api/v1",
  baseURL: "http://agendaandres.us-east-1.elasticbeanstalk.com:82/api/v1",
  headers: {
    "Content-type": "application/json"
  }
});