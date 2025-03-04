import axios from "axios";

export default axios.create({
  baseURL: "http://agendaandres.us-east-1.elasticbeanstalk.com:81/api/v1",
  //baseURL: "http://localhost:81/api/v1",
  headers: {
    "Content-type": "application/json"
  }
});