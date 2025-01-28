import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import { Link } from "react-router-dom";

export default class AddTutorial extends Component {
    constructor(props){

    }

    handleCheckboxChange = (event) => {
        this.setState({ published: event.target.checked });
    };

    saveTutorial = () => {
        const data = {
            id: this.state.id,
            title: this.state.title,
            description: this.state.description,
            published: this.state.published
        };

        TutorialDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    title: response.data.title,
                    description: response.data.description,
                    published: response.data.published,
                    submitted: true
                });
            })
    };


    render(){
        const { handleCheckboxChange, saveTutorial } = this.state;

        return (
            <div className="submit-form">
                <div>
                    <div className="form-group" id="form-group">
                        <label htmlFor="id">Id</label>
                        <input type="text" id="id" name="id" required />
                    </div>

                    <div className="form-group" id="form-group">
                        <label htmlFor="title">Title</label>
                        <input type="text" id="title" name="title" required />
                    </div>

                    <div className="form-group" id="form-group">
                        <label htmlFor="description">Description</label>
                        <input type="text" id="description" name="description" required />
                    </div>

                    <div className="form-group" id="form-group">
                        <label htmlFor="published">Published</label>
                        <input type="checkbox" id="published" name="published" onChange={this.handleCheckboxChange} />
                    </div>

                    <div>
                        <button type="submit" className="btn btn-success" onClick={this.saveTutorial}>
                            Submit
                        </button>
                    </div>
                </div>
            </div>
        
        );
    }

}