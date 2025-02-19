import React, { useState } from "react";  
import ProductDataService from "../services/product.service";
import "../styles/AddProduct.css";
import ProgressBar from "./progressBar.component";

const AddProduct = () => {
    // Definimos los estados para manejar los valores del formulario y el estado de envío
    //const [id, setId] = useState("");
    const [stock, setStock] = useState(0); 
    const [name, setName] = useState(""); 
    const [brand, setBrand] = useState(""); 
    const [price, setPrice] = useState(0); 
    const [active, setActive] = useState(false); 
    const [submitted, setSubmitted] = useState(false);

    // Maneja el cambio de estado del checkbox
    const handleCheckboxChange = (event) => {
        setActive(event.target.checked);
    };

    // Función para guardar el producto en la base de datos
    const saveProduct = () => {
        // Creamos un objeto con los datos ingresados
        const data = {
            //id,
            stock: stock,
            name: name,
            brand: brand,
            price, price,
            active: active
        };

        // Llamamos al servicio para crear el producto
        ProductDataService.create(data)
            .then(response => {
                // Si la respuesta es exitosa, actualizamos los estados con los valores de la base de datos
                //setId(response.data.id);
                setStock(response.data.stock);
                setName(response.data.name);
                setBrand(response.data.brand);
                setPrice(response.data.price);
                setActive(response.data.active);
                setSubmitted(true); // Cambiamos el estado a "enviado"
            })
            .catch(error => {
                console.error("Error adding product:", error);
            });        
    };

    // Función para reiniciar el formulario y agregar un nuevo producto
    const newProduct = () => {
        //setId(""); 
        setStock(""); 
        setName("");
        setBrand("");
        setPrice("");
        setActive(false); 
        setSubmitted(false); 
    };

    return (
        <div className="submit-form">
            {/* Si el formulario ha sido enviado, mostramos el mensaje de éxito y un botón para agregar otro */}
            { submitted ? (
                <div>
                    <h4>Product added successfully!</h4>
                    <button className="btn btn-success" onClick={newProduct}>
                        Add
                    </button> {/* Botón para agregar un nuevo producto */}
                </div>
            ) : (
                // Si el formulario no ha sido enviado, mostramos los campos de entrada
                <div>
                    <h4>NEW PRODUCT</h4>

                    {/* <div className="form-group">
                        <label htmlFor="id">Id</label>
                        <input type="text" className="form-control" id="id" name="id" required onChange={(e) => setId(e.target.value)} />
                    </div> */}

                    <div className="form-group">
                        <label htmlFor="stock">Stock</label>
                        <input type="number" className="form-control" id="stock" name="stock" required onChange={(e) => setStock(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input className="form-control" type="text" id="name" name="name" required onChange={(e) => setName(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="brand">Brand</label>
                        <input className="form-control" type="text" id="brand" name="brand" required onChange={(e) => setBrand(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input className="form-control" type="number" step="0.01" id="price" name="price" required onChange={(e) => setPrice(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="active">Active &nbsp;</label>
                        <input type="checkbox" id="active" name="active" onChange={handleCheckboxChange} />
                    </div>

                    <div>
                        <ProgressBar></ProgressBar>
                    </div>

                    {/* Botón para enviar los datos y guardar el producto */}
                    <div>
                        <button type="submit" className="btn btn-success" onClick={saveProduct}>
                            Submit
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default AddProduct;
