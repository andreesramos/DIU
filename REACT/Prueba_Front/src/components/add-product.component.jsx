import React, { useState, useEffect } from "react";
import ProductDataService from "../services/product.service";
import "../styles/AddProduct.css";
import ProgressBar from "./progressBar.component";
import { useNavigate } from "react-router-dom";

const MAX_PRODUCTS = 100;

const AddProduct = () => {
  // Estado para almacenar los valores del formulario y controlar la interacción del usuario.
  const [stock, setStock] = useState(0); // Cantidad de stock del producto.
  const [name, setName] = useState(""); // Nombre del producto.
  const [brand, setBrand] = useState(""); // Marca del producto.
  const [price, setPrice] = useState(0); // Precio del producto.
  const [active, setActive] = useState(false); // Estado de disponibilidad del producto (activo/inactivo).
  const [submitted, setSubmitted] = useState(false); // Indica si el formulario ha sido enviado.
  const [totalProducts, setTotalProducts] = useState(0); // Número total de productos registrados.
  const navigate = useNavigate(); // Hook para redirigir a otra página después de completar una acción.

  // Ejecuta la función `retrieveProductCount()` al montar el componente para obtener la cantidad total de productos.
  useEffect(() => {
    retrieveProductCount();
  }, []);

  // Función para obtener la cantidad total de productos desde el servicio.
  const retrieveProductCount = () => {
    ProductDataService.findAll()
      .then((response) => {
        setTotalProducts(response.data.length); // Guarda la cantidad total de productos.
      })
      .catch((error) => {
        console.error("Error fetching product count:", error); // Manejo de error en la obtención de productos.
      });
  };

  // Maneja el cambio en el checkbox de estado "activo".
  const handleCheckboxChange = (event) => {
    setActive(event.target.checked); // Actualiza el estado del producto según el checkbox seleccionado.
  };

  // Función para guardar un nuevo producto en la base de datos.
  const saveProduct = () => {
    // Si se ha alcanzado el límite máximo de productos, no permite agregar más.
    if (totalProducts >= MAX_PRODUCTS) return;

    // Crea un objeto con los datos ingresados por el usuario.
    const data = {
      stock,
      name,
      brand,
      price,
      active,
    };

    // Envía el nuevo producto al backend para guardarlo en la base de datos.
    ProductDataService.create(data)
      .then((response) => {
        // Actualiza los estados con los valores del producto recién guardado.
        setStock(response.data.stock);
        setName(response.data.name);
        setBrand(response.data.brand);
        setPrice(response.data.price);
        setActive(response.data.active);
        setSubmitted(true); // Indica que el producto se ha enviado correctamente.
        retrieveProductCount(); // Vuelve a contar los productos para reflejar el cambio.
      })
      .catch((error) => {
        console.error("Error adding product:", error); // Manejo de error en caso de fallo en la creación del producto.
      });
  };

  // Función para reiniciar el formulario y permitir ingresar un nuevo producto.
  const newProduct = () => {
    setStock(0); // Restablece el stock a 0.
    setName(""); // Borra el nombre ingresado.
    setBrand(""); // Borra la marca ingresada.
    setPrice(0); // Restablece el precio a 0.
    setActive(false); // Restablece el estado "activo" a `false`.
    setSubmitted(false); // Indica que el formulario está listo para un nuevo envío.
  };

  return (
    <div className="submit-form">
      {/* Si el formulario ha sido enviado, muestra mensaje de éxito y opciones */}
      {submitted ? (
        <div>
          <h4>Producto añadido correctamente!</h4>
          {/* Botón para agregar otro producto, deshabilitado si se alcanza el máximo */}
          <button
            className="btn btn-success"
            onClick={newProduct}
            disabled={totalProducts >= MAX_PRODUCTS}
          >
            Añadir
          </button>
          {/* Botón para volver a la lista de productos */}
          <button
            className="btn btn-primary"
            onClick={() => navigate("/products")}
          >
            Volver a la lista de productos
          </button>
        </div>
      ) : (
        // Si el formulario aún no ha sido enviado, muestra los campos de entrada
        <div>
          <h4>Nuevo Producto</h4>

          {/* Campo de entrada para el stock */}
          <div className="form-group">
            <label htmlFor="stock">Stock</label>
            <input
              type="number"
              min="0"
              className="form-control"
              id="stock"
              name="stock"
              required
              onChange={(e) => setStock(Number(e.target.value))}
            />
          </div>

          {/* Campo de entrada para el nombre del producto */}
          <div className="form-group">
            <label htmlFor="name">Nombre</label>
            <input
              className="form-control"
              type="text"
              id="name"
              name="name"
              required
              onChange={(e) => setName(e.target.value)}
            />
          </div>

          {/* Campo de entrada para la marca del producto */}
          <div className="form-group">
            <label htmlFor="brand">Marca</label>
            <input
              className="form-control"
              type="text"
              id="brand"
              name="brand"
              required
              onChange={(e) => setBrand(e.target.value)}
            />
          </div>

          {/* Campo de entrada para el precio del producto */}
          <div className="form-group">
            <label htmlFor="price">Precio</label>
            <input
              className="form-control"
              type="number"
              step="0.01"
              id="price"
              name="price"
              required
              onChange={(e) => setPrice(Number(e.target.value))}
            />
          </div>

          {/* Checkbox para definir si el producto está activo o inactivo */}
          <div className="form-group">
            <label htmlFor="active">Activo &nbsp;</label>
            <input
              type="checkbox"
              id="active"
              name="active"
              onChange={handleCheckboxChange}
            />
          </div>

          {/* Muestra la barra de progreso con el total de productos y el máximo permitido */}
          <div>
            <ProgressBar value={totalProducts} max={MAX_PRODUCTS} />
          </div>

          {/* Muestra un mensaje si se ha alcanzado el límite de productos */}
          {totalProducts >= MAX_PRODUCTS && (
            <p className="text-danger">
              Límite de productos alcanzado. No puedes añadir más.
            </p>
          )}

          {/* Botón para crear un nuevo producto, deshabilitado si se alcanzó el máximo */}
          <div>
            <button
              type="submit"
              className="btn btn-success"
              onClick={saveProduct}
              disabled={totalProducts >= MAX_PRODUCTS}
            >
              Crear
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default AddProduct;
