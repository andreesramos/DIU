const ListaMonedas = ({coins: coins, onSelect, selected}) => {
  return (
    <div>
      {coins.length > 0 ? ( 
        <table>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Descripcion</th>
              <th>Stock</th>
            </tr>
          </thead>
          <tbody>
            {coins.map((product) => (
              <tr
                key={product.id}
                onClick={() => onSelect(product)}
                className={selected?.id === product.id ? "selected" : ""}
              >
                <td>{product.name}</td>
                <td>{product.brand}</td>
                <td>{product.stock}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Cargando monedas...</p>
      )}

      {selected ? (
        <p>Seleccionado: {selected.name}</p>
      ) : (
        <p>Selecciona una moneda:</p>
      )}
    </div>
  )
}

export default ListaMonedas