import React, { useState, useEffect } from "react";
import ProductDataService from "../services/product.service";
import "../styles/AddProduct.css";
import { useParams, useNavigate } from "react-router-dom";

const EditProduct = () => {
  const { id } = useParams(); // Obtiene el ID del producto desde la URL.
  const [stock, setStock] = useState(0); // Estado para almacenar la cantidad de stock del producto.
  const [name, setName] = useState(""); // Estado para almacenar el nombre del producto.
  const [brand, setBrand] = useState(""); // Estado para almacenar la marca del producto.
  const [price, setPrice] = useState(0); // Estado para almacenar el precio del producto.
  const [active, setActive] = useState(false); // Estado para indicar si el producto está activo o no.
  const [submitted, setSubmitted] = useState(false); // Estado para manejar si el formulario ha sido enviado correctamente.
  const [errorStock, setErrorStock] = useState(""); // Estado para manejar errores en la validación del stock.
  const [errorPrice, setErrorPrice] = useState(""); // Estado para manejar errores en la validación del precio.
  const navigate = useNavigate(); // Hook para navegar entre páginas.

  useEffect(() => {
    getProduct(id); // Llama a la función `getProduct` al montar el componente o si cambia el ID.
  }, [id]);

  // Función para obtener el producto desde el backend y actualizar los estados con su información.
  const getProduct = (id) => {
    ProductDataService.findAll()
      .then((response) => {
        // Busca el producto en la lista obtenida.
        const product = response.data.find((item) => item.id.toString() === id);
        if (product) {
          setStock(product.stock);
          setName(product.name);
          setBrand(product.brand);
          setPrice(product.price);
          setActive(product.active);
        }
      })
      .catch((error) => {
        console.error("Error fetching products:", error);
      });
  };

  // Maneja el cambio de valor en el campo de stock.
  const handleStockChange = (e) => {
    const value = Number(e.target.value);
    setStock(value);
    // Valida que el stock no sea negativo.
    if (value < 0) {
      setErrorStock("El stock no puede ser menor a 0");
    } else {
      setErrorStock("");
    }
  };

  // Maneja el cambio de valor en el campo de precio.
  const handlePriceChange = (e) => {
    const value = Number(e.target.value);
    setPrice(value);
    // Valida que el precio no sea menor a 0.01.
    if (value < 0.01) {
      setErrorPrice("El precio no puede ser menor a 0.01");
    } else {
      setErrorPrice("");
    }
  };

  // Maneja el cambio de estado del checkbox "Activo".
  const handleCheckboxChange = (event) => {
    setActive(event.target.checked);
  };

  // Función para actualizar el producto en el backend.
  const updateProduct = () => {
    // Evita actualizar si el stock o el precio no son válidos.
    if (stock < 0 || price < 0.01) return;

    // Crea el objeto con los datos actualizados del producto.
    const data = {
      id,
      stock,
      name,
      brand,
      price,
      active,
    };

    // Envía la actualización al backend.
    ProductDataService.update(id, data)
      .then(() => {
        setSubmitted(true); // Marca que la actualización fue exitosa.
      })
      .catch((error) => {
        console.error("Error updating product:", error);
      });
  };

  // Renderizado condicional para mostrar el formulario o el mensaje de éxito.
  return (
    <div className="submit-form">
      {submitted ? (
        // Si el producto fue actualizado, muestra un mensaje de éxito y un botón para volver a la lista.
        <div>
          <h4>Producto actualizado correctamente!</h4>
          <button
            className="btn btn-primary mt-2"
            onClick={() => navigate("/products")}
          >
            Volver a la lista de productos
          </button>
        </div>
      ) : (
        // Formulario de edición del producto.
        <div>
          <h4>Editar producto</h4>

          {/* Campo de entrada para el stock */}
          <div className="form-group">
            <label htmlFor="stock">Stock</label>
            <input
              type="number"
              min="0"
              className="form-control"
              id="stock"
              name="stock"
              value={stock}
              required
              onChange={handleStockChange}
            />
            {errorStock && <p className="text-danger">{errorStock}</p>}
          </div>

          {/* Campo de entrada para el nombre */}
          <div className="form-group">
            <label htmlFor="name">Nombre</label>
            <input
              className="form-control"
              type="text"
              id="name"
              name="name"
              value={name}
              required
              onChange={(e) => setName(e.target.value)}
            />
          </div>

          {/* Campo de entrada para la marca */}
          <div className="form-group">
            <label htmlFor="brand">Marca</label>
            <input
              className="form-control"
              type="text"
              id="brand"
              name="brand"
              value={brand}
              required
              onChange={(e) => setBrand(e.target.value)}
            />
          </div>

          {/* Campo de entrada para el precio */}
          <div className="form-group">
            <label htmlFor="price">Precio</label>
            <input
              className="form-control"
              type="number"
              step="0.01"
              id="price"
              name="price"
              value={price}
              required
              onChange={handlePriceChange}
            />
            {errorPrice && <p className="text-danger">{errorPrice}</p>}
          </div>

          {/* Checkbox para marcar el producto como activo o inactivo */}
          <div className="form-group">
            <label htmlFor="active">Activo &nbsp;</label>
            <input
              type="checkbox"
              id="active"
              name="active"
              checked={active}
              onChange={handleCheckboxChange}
            />
          </div>

          {/* Botón para actualizar el producto */}
          <div>
            <button
              type="submit"
              className="btn btn-success"
              onClick={updateProduct}
              disabled={errorStock || errorPrice} // Deshabilita el botón si hay errores en el stock o el precio.
            >
              Actualizar
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default EditProduct;
