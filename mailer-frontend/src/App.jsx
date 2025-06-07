// src/App.jsx
import React, { useState } from 'react';
import axios from 'axios';
import './App.css'; // Import your CSS file

const templates = {
  default: `Hi {{person}},\n\nI hope you are doing well. I am writing to express my interest in the {{post}} role at {{company}}. Please find my resume attached.\n\nBest regards,\nAnkit Bhujeja`,
  friendly: `Hello {{person}},\n\nI came across the {{post}} opening at {{company}} and was excited to apply. I've attached my resume for your review.\n\nWarm regards,\nAnkit Bhujeja`
};
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;
export default function App() {
  const [formData, setFormData] = useState({
    person: '',
    company: '',
    post: '',
    email: '',
    template: 'cold_email'
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`${API_BASE_URL}/api/mail/send`, formData);
      alert('Email sent successfully!');
    } catch (err) {
      console.error(err);
      alert('Failed to send email.');
    }
  };

  return (
    <div className="container">
      <form onSubmit={handleSubmit} className="form">
        <h1>HireNote</h1>
        <input name="person" placeholder="Person Name" onChange={handleChange} required />
        <input name="company" placeholder="Company Name" onChange={handleChange} required />
        <input name="post" placeholder="Post Name" onChange={handleChange} required />
        <input name="email" type="email" placeholder="Email ID" onChange={handleChange} required />
        <select name="template" onChange={handleChange}>
          <option value="linkedin_post">Recently Linkedin Post</option>
          <option value="cold_email">Cold Email Template</option>
          <option value="referral_request">Referral Request Template</option>
          <option value="recruiter_outreach">Recruiter Outreach Template</option>
          <option value="follow_up">Follow up on Job Board Applied Template</option>
        </select>
        <button type="submit">Send Email</button>
      </form>
    </div>
  );
}
