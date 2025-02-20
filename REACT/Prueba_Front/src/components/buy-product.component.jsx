import React, { useState, useEffect } from "react";
import ProductDataService from "../services/product.service";
import { useParams, useNavigate } from "react-router-dom";

const PurchaseProduct = () => {
  const { id } = useParams(); // Obtiene el ID del producto desde la URL.
  const navigate = useNavigate(); // Hook para la navegación entre páginas.
  const [product, setProduct] = useState(null); // Estado para almacenar el producto seleccionado.
  const [purchaseQuantity, setPurchaseQuantity] = useState(0); // Estado para la cantidad a comprar.
  const [error, setError] = useState(""); // Estado para manejar errores en la compra.
  const [success, setSuccess] = useState(false); // Estado para manejar el éxito de la compra.

  // Ejecuta la función `getProduct` al montar el componente o si el ID cambia.
  useEffect(() => {
    getProduct(id);
  }, [id]);

  // Obtiene la lista de productos y busca el que coincide con el ID.
  const getProduct = (id) => {
    ProductDataService.findAll()
      .then((response) => {
        const foundProduct = response.data.find(
          (item) => item.id.toString() === id
        );
        if (foundProduct) {
          setProduct(foundProduct); // Almacena el producto en el estado.
        }
      })
      .catch((error) => {
        console.error("Error obteniendo productos:", error);
        setError("Error al obtener el producto."); // Manejo de error si no se puede recuperar el producto.
      });
  };

  // Maneja la compra del producto, verificando condiciones antes de actualizar el stock.
  const handlePurchase = () => {
    if (!product) return; // Si el producto no existe, no se ejecuta la compra.

    const quantity = Number(purchaseQuantity); // Convierte la cantidad ingresada a número.

    // Valida que la cantidad a comprar sea mayor a 0.
    if (quantity <= 0) {
      setError("La cantidad debe ser mayor que 0.");
      return;
    }

    // Verifica que haya suficiente stock para la compra.
    if (quantity > product.stock) {
      setError("No hay suficiente stock disponible.");
      return;
    }

    // Crea un objeto con el stock actualizado después de la compra.
    const updatedProduct = {
      ...product,
      stock: product.stock - quantity,
    };

    // Llama al servicio para actualizar el producto con el nuevo stock.
    ProductDataService.update(product.id, updatedProduct)
      .then(() => {
        setProduct(updatedProduct); // Actualiza el estado con el nuevo stock.
        setError(""); // Limpia los errores en caso de éxito.
        setSuccess(true); // Marca la compra como exitosa.
      })
      .catch((error) => {
        console.error("Error al procesar la compra:", error);
        setError("Error al procesar la compra."); // Manejo de error si la actualización falla.
      });
  };

  return (
    <div className="submit-form">
      {success ? (
        // Si la compra es exitosa, muestra un mensaje y un botón para volver a la lista de productos.
        <div>
          <h4>Compra realizada con éxito!</h4>
          <button
            className="btn btn-primary mt-2"
            onClick={() => navigate("/products")}
          >
            Volver a la lista de productos
          </button>
        </div>
      ) : (
        // Si la compra aún no ha sido realizada, muestra el formulario de compra.
        <div>
          {error && <div className="alert alert-danger">{error}</div>}

          {product ? (
            <div>
              <h4>Comprar Producto</h4>
              <p>
                <strong>Nombre:</strong> {product.name}
              </p>
              <p>
                <strong>Stock disponible:</strong> {product.stock}
              </p>
              <p>
                <strong>Precio:</strong> {product.price} €
              </p>

              {/* Campo de entrada para ingresar la cantidad a comprar */}
              <div className="form-group mt-3">
                <label>
                  <strong>Cantidad a comprar:</strong>
                </label>
                <input
                  type="number"
                  className="form-control"
                  min="1"
                  value={purchaseQuantity}
                  onChange={(e) => setPurchaseQuantity(e.target.value)}
                />
              </div>

              {/* Botón para confirmar la compra */}
              <button
                className="btn btn-primary mt-2"
                onClick={handlePurchase}
                disabled={product.stock === 0} // Deshabilita el botón si no hay stock.
              >
                Comprar
              </button>

              {/* Botón para cancelar y volver a la lista de productos */}
              <button
                className="btn btn-secondary mt-2 ml-2"
                onClick={() => navigate("/products")}
              >
                Cancelar
              </button>
            </div>
          ) : (
            // Muestra un mensaje de carga mientras se obtiene la información del producto.
            <div>
              <p>Cargando producto...</p>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default PurchaseProduct;
