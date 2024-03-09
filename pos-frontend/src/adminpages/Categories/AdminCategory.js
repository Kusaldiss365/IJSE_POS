/**
 * The `AdminCategory` function in this code is responsible for managing categories in an admin panel,
 * allowing for adding, editing, and deleting categories through API calls.
 * @returns The `AdminCategory` component is being returned. It consists of JSX elements that render an
 * admin panel for managing categories. The component includes a navigation bar, a card displaying
 * categories with options to add, edit, and delete categories. It fetches categories from an API
 * endpoint, displays them in a table, and provides functionality to edit and delete categories.
 * Additionally, it includes a component for adding new categories
 */

import React, { useEffect, useState } from 'react';
import { Button, Card, Row, Table } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import AdminNavBar from '../components/AdminNavBar';
import AddCategories from './AddCategories';
import EmptyContent from '../../utils/EmptyContent';
import { DeleteButton, UpdateButton } from '../../utils/Buttons';

export default function AdminCategory() {
    const [addcategories, setAddCategories] = useState({ display: false });
    const [categories, setCategories] = useState([]);
    const [loadingBTN, setLoadingBTN] = useState({ delete: true, loadingID: null });
    const navigate = useNavigate();

    useEffect(() => {
        getCategories();
    }, []);

    const getCategories = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/v1/categories");
            setCategories(response.data.data || []);
        } catch (error) {
            console.error(error);
            if (error.response && error.response.status === 401) {
                navigate("/admin/login");
            } else {
                setCategories([]);
            }
        }
    }

    const handleEdit = (category) => {
        setAddCategories({ display: true, editing: category });
    };

    const handleDelete = async (categoryId) => {
        setLoadingBTN({ delete: true, loadingID: categoryId });

        try {
            await axios.delete(`http://localhost:8080/api/v1/categories?id=${categoryId}`);
            const updatedCategories = categories.filter((category) => category.categoryId !== categoryId);
            setCategories(updatedCategories);
            setLoadingBTN({ delete: false, loadingID: categoryId });
        } catch (error) {
            setLoadingBTN({ delete: false, loadingID: categoryId });
            if (error.response && error.response.status === 401) {
                navigate('/admin/login');
            }
        }
    };

    return (
        <div>
            <AdminNavBar />
            <div className='container-md bg-white d-flex flex-column align-items-center'>
                <Card className='border-none' style={{ border: "none", width: "100%" }}>
                    <Row className='d-flex flex-column align-items-center mt-4'>
                        <div className='col-md-8 px-4' style={{ backgroundColor: "#004080", color: 'white', padding: "10px", borderRadius: "5px" }}>
                            <div className='d-flex flex-row ' style={{ justifyContent: "space-between" }}>
                                <h3>Categories</h3>
                                <div>
                                    <Button onClick={() => setAddCategories({ display: true })}>+ Add category</Button>
                                </div>
                            </div>
                        </div>
                    </Row>
                </Card>
                {categories.length > 0 && (
                    <Table className="category-table">
                        <colgroup>
                            <col style={{ width: '10%' }} />
                            <col style={{ width: '20%' }} />
                            <col style={{ width: '50%' }} />
                            <col style={{ width: '30%' }} />
                        </colgroup>
                        <thead>
                            <tr>
                                <th style={{ textAlign: 'center' }}>ID</th>
                                <th style={{ textAlign: 'center' }}>Name</th>
                                <th style={{ textAlign: 'center' }}>Description</th>
                                <th style={{ textAlign: 'center' }}>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {categories.map((category) => (
                                <tr key={category.categoryId}>
                                    <td>{category.categoryId}</td>
                                    <td>{category.category}</td>
                                    <td>{category.description}</td>
                                    <td>
                                        <UpdateButton onClick={handleEdit} item={category} />
                                        <DeleteButton
                                            shouldLoad={loadingBTN.loadingID === category.categoryId}
                                            loadingBTN={loadingBTN}
                                            onClick={handleDelete}
                                            item={category.categoryId} />
                                        <button className="delete-btn" onClick={() => handleDelete(category.categoryId)}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                )}
                {EmptyContent(categories)}
            </div>
            <AddCategories categories={categories} setCategories={setCategories} addcategories={addcategories} setAddCategories={setAddCategories} />
        </div>
    )
}
