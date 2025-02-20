import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ProductDataService from "../services/product.service";
import "../styles/ListProducts.css";
import "../styles/BuscarNombre.css";

const ProductsList = () => {
  const [products, setProducts] = useState([]);
  const [allProducts, setAllProducts] = useState([]);
  const [currentProduct, setCurrentProduct] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchName, setSearchName] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    retrieveProducts();
  }, []);

  const onChangeSearchName = (e) => {
    // Convierte el valor del campo de búsqueda a minúsculas para evitar problemas de sensibilidad a mayúsculas/minúsculas.
    const searchTerm = e.target.value.toLowerCase();
    setSearchName(searchTerm);

    // Si el campo de búsqueda está vacío, restaura la lista de productos original.
    if (searchTerm === "") {
      setProducts(allProducts);
    } else {
      // Filtra los productos cuyo nombre contenga el término de búsqueda.
      const filteredProducts = allProducts.filter((product) =>
        product.name.toLowerCase().includes(searchTerm)
      );
      // Actualiza la lista de productos con los que coinciden con la búsqueda.
      setProducts(filteredProducts);
    }
  };

  const retrieveProducts = () => {
    // Obtiene todos los productos del servicio de datos.
    ProductDataService.findAll()
      .then((response) => {
        // Guarda los productos obtenidos en los estados correspondientes.
        setProducts(response.data);
        setAllProducts(response.data);
        console.log(response.data); // Muestra en la consola los productos obtenidos.
      })
      .catch((e) => {
        console.log(e); // Captura y muestra cualquier error en la consola.
      });
  };

  const refreshList = () => {
    // Vuelve a obtener la lista de productos desde el servidor.
    retrieveProducts();
    // Reinicia el producto seleccionado y el índice actual.
    setCurrentProduct(null);
    setCurrentIndex(-1);
    setError(""); // Reinicia el mensaje de error cuando se refresca la lista.
  };

  const selectProduct = (product, index) => {
    // Establece el producto seleccionado y su índice en la lista.
    setCurrentProduct(product);
    setCurrentIndex(index);

    // Establece el mensaje de error según el estado del producto
    if (!product.active) {
      setError("Producto inactivo");
    } else if (product.stock === 0) {
      setError("Producto agotado, stock = 0");
    } else {
      setError(""); // No hay error si el producto es válido para la compra
    }
  };

  const removeAllProducts = async () => {
    try {
      // Obtiene la lista completa de productos desde el servicio.
      const response = await ProductDataService.findAll();
      const products = response.data;

      // Si no hay productos, muestra un mensaje en la consola y termina la función.
      if (products.length === 0) {
        console.log("No hay productos para eliminar.");
        return;
      }

      // Recorre la lista de productos y los elimina uno por uno.
      for (const product of products) {
        try {
          await ProductDataService.delete(product.id);
          console.log(
            `Producto eliminado: ${product.name} (ID: ${product.id})`
          );
        } catch (error) {
          console.error(`Error al eliminar el producto ${product.id}:`, error);
        }
      }

      console.log("Todos los productos han sido eliminados.");
      refreshList(); // Refresca la lista después de eliminar todos los productos.
    } catch (error) {
      console.error("Error al obtener la lista de productos:", error);
    }
  };

  // const searchByName = () => {
  //   ProductDataService.findByName(searchName)
  //     .then((response) => {
  //       setProducts(response.data);
  //       console.log(response.data);
  //     })
  //     .catch((e) => {
  //       console.log(e);
  //     });
  // };

  return (
    <div className="list row">
      <div className="col-md-8">
        <div className="search-container">
          <input
            type="text"
            className="search-input"
            placeholder="Buscar producto por nombre..."
            value={searchName}
            onChange={onChangeSearchName}
          />
          {/* <div className="input-group-append">
            <button className="btn btn-outline-secondary" type="button" onClick={searchByName}>
              Search
            </button>
          </div> */}
        </div>
      </div>
      <div className="col-md-6">
        <h4>Lista de productos</h4>
        <ul className="list-group">
          {products &&
            products.map((product, index) => (
              <li
                className={
                  "list-group-item " + (index === currentIndex ? "active" : "")
                }
                onClick={() => selectProduct(product, index)}
                key={index}
              >
                {product.name}
              </li>
            ))}
        </ul>
        <button
          className="m-3 btn btn-sm btn-danger"
          onClick={removeAllProducts}
        >
          Eliminar todos
        </button>
      </div>
      <div className="col-md-6">
        {currentProduct ? (
          <div>
            <h4>Producto</h4>
            <div>
              <label>
                <strong>Nombre:</strong>
              </label>{" "}
              {currentProduct.name}
            </div>
            <div>
              <label>
                <strong>Stock:</strong>
              </label>{" "}
              {currentProduct.stock}
            </div>
            <div>
              <label>
                <strong>Marca:</strong>
              </label>{" "}
              {currentProduct.brand}
            </div>
            <div>
              <label>
                <strong>Precio:</strong>
              </label>{" "}
              {currentProduct.price}
            </div>
            <div>
              <label>
                <strong>Activo:</strong>
              </label>{" "}
              {currentProduct.active ? "Active" : "Inactive"}
            </div>

            {/* Renderizado condicional para el botón de compra */}
            {currentProduct.active && currentProduct.stock > 0 ? (
              <Link to={`/products/${currentProduct.id}/buy`} className="badge badge-success">
                Comprar
              </Link>
            ) : (
              <button className="badge badge-secondary" disabled>
                Comprar
              </button>
            )}

            <Link
              to={`/products/${currentProduct.id}`}
              className="badge badge-warning"
            >
              Editar
            </Link>
            <Link
              to={`/products/${currentProduct.id}/delete`}
              className="badge badge-error"
            >
              Eliminar
            </Link>

            <div>
              {/* Mensaje de error si el producto no está disponible */}
              {error && <p className="text-danger">{error}</p>}
            </div>
          </div>
        ) : (
          <div>
            <br />
            <p>Pulsa en un producto...</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductsList;
