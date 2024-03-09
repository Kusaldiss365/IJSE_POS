/**
 * The function `EmptyContent` displays a loading spinner if the list is null or an empty content
 * message if the list has no items.
 * @returns The `EmptyContent` function returns either a loading spinner with the text "Loading..." if
 * the `list` is `null`, or an image and text saying "Empty" if the `list` is an empty array.
 */

import React from 'react';
import { Spinner } from 'react-bootstrap';

export default function EmptyContent(list) {
  if (list === null) {
    return (
      <Spinner animation="border" role="status" className='m-4'>
        <span className="visually-hidden">Loading...</span>
      </Spinner>
    );
  } else if (list.length === 0) {
    return (
      <div className='d-flex flex-column align-items-center'>
        <img src='/empty.png' />
        <h3>Empty</h3>
      </div>
    );
  }
}
