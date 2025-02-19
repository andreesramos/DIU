import http from "../http-common";

class ProductDataService {
    create(data){
        return http.post("/products", data);
    }

    delete(id){
        return http.delete(`/products/${id}`);
    }

    update(id, data){
        return http.put(`/products/${id}`, data);
    }

    findAll(){
        return http.get("/products");
    }
}

export default new ProductDataService();