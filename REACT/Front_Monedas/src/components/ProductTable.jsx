import "../styles/Conversor1.css";

// Componente para mostrar una tabla de productos
const ProductTable = ({ title, products, onSelect, selected }) => {
  return (
    <div>
      <h3>{title}</h3>
      {products.length > 0 ? ( // Renderizado condicional si hay productos
        <table>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Precio</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr
                key={product.id}
                onClick={() => onSelect(product)}
                className={selected?.id === product.id ? "selected" : ""} // Resalta la fila seleccionada
              >
                <td>{product.name}</td>
                <td>${product.price.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Cargando productos...</p> // Mensaje de carga si aún no hay productos
      )}
      <p>Seleccionado: {selected ? selected.name : "Ninguno"}</p>
    </div>
  );
};

export default ProductTable;
