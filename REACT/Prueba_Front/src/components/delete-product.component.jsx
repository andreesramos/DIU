import React, { useState } from "react";
import ProductDataService from "../services/product.service";
import { useParams, useNavigate } from "react-router-dom";

const DeleteProduct = () => {
  const { id } = useParams(); // Obtiene el ID del producto desde la URL.
  const navigate = useNavigate(); // Hook para redirigir a otras páginas.
  const [deleted, setDeleted] = useState(false); // Estado para indicar si el producto ha sido eliminado.
  const [error, setError] = useState(""); // Estado para manejar errores en la eliminación.

  // Función para eliminar un producto por su ID.
  const deleteProduct = () => {
    ProductDataService.delete(id)
      .then(() => {
        setDeleted(true); // Marca el estado como eliminado si la operación es exitosa.
      })
      .catch((error) => {
        console.error("Error eliminando producto:", error);
        setError("Error al eliminar el producto"); // Maneja el error si la eliminación falla.
      });
  };

  return (
    <div className="submit-form">
      {deleted ? (
        // Si el producto ha sido eliminado, muestra un mensaje de confirmación y un botón para volver a la lista.
        <div>
          <h4>Producto eliminado correctamente!</h4>
          <button
            className="btn btn-primary mt-2"
            onClick={() => navigate("/products")}
          >
            Volver a la lista de productos
          </button>
        </div>
      ) : (
        // Si el producto aún no ha sido eliminado, muestra la confirmación.
        <div>
          {/* Muestra un mensaje de error si ocurre un problema al eliminar el producto */}
          {error && <div className="alert alert-danger">{error}</div>}

          <h4>¿Estás seguro de que quieres eliminar este producto?</h4>

          {/* Botón para confirmar la eliminación */}
          <button
            type="button"
            className="btn btn-danger"
            onClick={deleteProduct}
          >
            Eliminar
          </button>

          {/* Botón para cancelar y regresar a la lista de productos */}
          <button
            type="button"
            className="btn btn-secondary ml-2"
            onClick={() => navigate("/products")}
          >
            Cancelar
          </button>
        </div>
      )}
    </div>
  );
};

export default DeleteProduct;
