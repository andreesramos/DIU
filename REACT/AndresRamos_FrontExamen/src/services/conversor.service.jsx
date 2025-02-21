import service from "../http-common";

export const findAll = () => service.get("/products");

export const create = (data) => service.post("/products", data);

export const updateCurrency = (id, data) => service.put(`/products/${id}`, data);

export const deleteCurrency = (id) => service.delete(`/products/${id}`);
