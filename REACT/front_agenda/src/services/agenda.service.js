import http from "../http-common2";

class AgendaDataService {
  getAll() {
    return http.get("/agenda");
  }

  get(id) {
    return http.get(`/agenda/${id}`);
  }

  create(data) {
    return http.post("/agenda", data);
  }

  update(id, data) {
    return http.put(`/agenda/${id}`, data);
  }

  delete(id) {
    return http.delete(`/agenda/${id}`);
  }

  deleteAll() {
    return http.delete(`/agenda`);
  }

 // findByTitle(title) {
 //   return http.get(`/tutorials?title=${title}`);
 // }
 findByNombre(nombre) {
     return http.get(`/agenda/nombre/${nombre}`);
   }
}

export default new AgendaDataService();