import service from "../http-common";

// Obtener todos los productos
export const getAllProducts = () => service.get("/products");

// Obtener un producto por ID
export const getProductById = (id) => service.get(`/products/${id}`);

// Crear un nuevo producto
export const createProduct = (data) => service.post("/products", data);

// Actualizar un producto
export const updateProduct = (id, data) => service.put(`/products/${id}`, data);

// Eliminar un producto
export const deleteProduct = (id) => service.delete(`/products/${id}`);

// Eliminar todos los productos
export const deleteAllProducts = () => service.delete(`/products`);