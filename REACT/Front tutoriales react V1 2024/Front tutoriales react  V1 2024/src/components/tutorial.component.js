import React, { useState, useEffect } from "react";
import TutorialDataService from "../services/tutorial.service";
import { useParams } from "react-router-dom";

const Tutorial = () => {
    const { id } = useParams();
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [published, setPublished] = useState(false);
    const [submitted, setSubmitted] = useState(false);

    useEffect(() => {
        getTutorial(id);
    }, [id]);

    const getTutorial = (id) => {
        TutorialDataService.get(id)
            .then(response => {
                setTitle(response.data.title);
                setDescription(response.data.description);
                setPublished(response.data.published);
            })
            .catch(error => {
                console.error("Error fetching tutorial:", error);
            });
    };

    const handleCheckboxChange = (event) => {
        setPublished(event.target.checked);
    };

    const updateTutorial = () => {
        const data = {
            title,
            description,
            published
        };

        TutorialDataService.update(id, data)
            .then(response => {
                setSubmitted(true);
            })
            .catch(error => {
                console.error("Error updating tutorial:", error);
            });
    };

    return (
        <div className="submit-form">
            {
                submitted ? (
                    <div>
                        <h4>Tutorial updated successfully!</h4>
                    </div>
                ) : (
                    <div>
                    <div className="form-group">
                        <label htmlFor="id">Id</label>
                        <input type="text" className="form-control" id="id" name="id" value={id} required readOnly />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input type="text" className="form-control" id="title" name="title" value={title} required onChange={(e) => setTitle(e.target.value)} />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <input className="form-control" type="text" id="description" name="description" value={description} required onChange={(e) => setDescription(e.target.value)} />
                    </div>
    
                    <div className="form-group">
                        <label htmlFor="published">Published &nbsp;</label>
                        <input type="checkbox" id="published" name="published" checked={published} onChange={handleCheckboxChange} />
                    </div>
    
                    <div>
                        <button type="submit" className="btn btn-success" onClick={updateTutorial}>
                            Update
                        </button>
                    </div>
                </div>
                )
            }
            
        </div>
    );
};

export default Tutorial;
