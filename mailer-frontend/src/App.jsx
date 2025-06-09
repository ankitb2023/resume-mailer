// src/App.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;
export default function App() {
   const defaultState = {
    person: '',
    company: '',
    post: '',
    email: '',
    template: 'cold_email'
  }
  const [isSubmitting,setIsSubmitting] = useState(false);
  const [formData, setFormData] = useState(defaultState);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    try {
      const response = await axios.post(`${API_BASE_URL}/api/mail/send`, formData);
      toast.success('Email sent successfully!');
      setFormData(defaultState);
    } catch (err) {
      console.error(err);
      toast.error('Failed to send email.');
    } finally{
      setIsSubmitting(false);
    }
  };

   useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const mailto = params.get("mailto");

    if (mailto && mailto.startsWith("mailto:")) {
      const email = mailto.replace("mailto:", "").trim();
      setFormData((prev) => ({
        ...prev,
        email,
      }));
    }
  }, []);
  return (<>
    <div className="container">
      <form onSubmit={handleSubmit} className="form">
        <h1>HireNote</h1>
        <input name="person" placeholder="Person Name" value={formData.person} onChange={handleChange} required />
        <input name="company" placeholder="Company Name" value={formData.company} onChange={handleChange} required />
        <input name="post" placeholder="Post Name" value={formData.post} onChange={handleChange} required />
        <input name="email" type="email" placeholder="Email ID" value={formData.email} onChange={handleChange} required />
        <select name="template" value={formData.template} onChange={handleChange}>
          <option value="linkedin_post">Recently Linkedin Post</option>
          <option value="cold_email">Cold Email Template</option>
          <option value="referral_request">Referral Request Template</option>
          <option value="recruiter_outreach">Recruiter Outreach Template</option>
          <option value="follow_up">Follow up on Job Board Applied Template</option>
        </select>
        <button type="submit" disabled={isSubmitting}>{isSubmitting ? 'Sending...' : 'Send Email'}</button>
      </form>
    </div>
  <ToastContainer position="top-right" />
    </>
  );
}
