/* This code snippet is a React functional component named `AddCategories` that allows users to add or
edit categories. Here's a breakdown of what the code does: */

import React, { useState, useEffect } from 'react';
import { Modal } from 'react-bootstrap';
import axios from 'axios';

const categorytypes = [
    "FRUIT",
    "VEGETABLE",
    "MILK_POWER",
    "FURNITURE",
    "ELECTRONIC",
    "FISH",
    "MEAT"
];

export default function AddCategories({ addcategories, setaddcategories, setCategories, categories }) {
    const [categoryDescription, setcategoryDescription] = useState("");
    const [CategoryType, setCategoryType] = useState("FRUIT");

    const handleSUbmit = async (e) => {
        e.preventDefault();
        try {
            if (addcategories.editing) {
                await update();
            } else {
                await createCategory();
            }
        } catch (error) {
            console.log(error);
        }
    }

    const update = async () => {
        const response = await axios.put(`http://localhost:8080/api/v1/categories?id=${addcategories.editing.categoryId}`, {
            description: categoryDescription,
            category: CategoryType
        });
        setaddcategories({ display: false });

        const updatedCategories = categories.map((category) =>
            category.categoryId === addcategories.editing.categoryId ? {
                ...category,
                category: CategoryType,
                description: categoryDescription
            } : category
        );
        setCategories(updatedCategories);
    }

    const createCategory = async () => {
        const response = await axios.post('http://localhost:8080/api/v1/categories', {
            description: categoryDescription,
            category: CategoryType
        });
        setaddcategories({ display: false });

        setCategories([...categories, {
            categoryId: response.data.data,
            category: CategoryType,
            description: categoryDescription
        }]);
    }

    useEffect(() => {
        if (addcategories.editing) {
            setcategoryDescription(addcategories.editing.description);
            setCategoryType(addcategories.editing.category);
        } else {
            setcategoryDescription("");
        }
    }, [addcategories])

    const hidemodal = () => {
        setaddcategories({ display: false });
    }

    return (
        <Modal show={addcategories.display} onHide={hidemodal}>
            <form onSubmit={handleSUbmit}>
                <Modal.Header closeButton>
                    <Modal.Title>Add/Edit category</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <label htmlFor="categoryName">Category Type:</label>
                    <select className='form-select' value={CategoryType} onChange={(e) => { setCategoryType(e.target.value) }}>
                        {categorytypes.map(item =>
                            <option key={item} value={item}>{item}</option>
                        )}
                    </select>

                    <label htmlFor="categoryDescription">Category Description:</label>
                    <input value={categoryDescription} className='form-control' type="text" onChange={(e) => { setcategoryDescription(e.target.value) }} required />
                </Modal.Body>
                <Modal.Footer>
                    <button type="button" className="btn btn-secondary" onClick={hidemodal}>Close</button>
                    <button type="submit" className="btn btn-primary">{addcategories.editing ? "Update category" : "Create category"}</button>
                </Modal.Footer>
            </form>
        </Modal>
    )
}
