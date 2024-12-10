import React, {Component} from "react";

export default class InputComponent extends Component{
    render(){
        return(
            <div>
                <input value={this.props.nombre} onChange={this.props.cambiarNombre}></input>
                {/*Hacemos que el valor que aparezca inicialmente sea el del atributo "name" del props del input */}
            </div>
        )
    }
}