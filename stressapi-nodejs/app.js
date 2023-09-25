const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const User = require('./user');

const app = express();
const port = process.env.PORT || 3000;
const mongoURI = 'mongodb://localhost:27017/coredb?retryWrites=true'; // Replace with your MongoDB URI

mongoose.connect(mongoURI, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => {
    console.log('Connected to MongoDB');
  })
  .catch((error) => {
    console.error('MongoDB connection error:', error);
  });

app.use(bodyParser.json());

app.post('/user', async (req, res) => {
  try {
    const user = new User(req.body);
    const savedUser = await user.save();
    res.status(201).json(savedUser);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

app.get('/user/:username', async (req, res) => {
  const username = req.params.username;
  try {
    const user = await User.findOne({ username });
    res.status(200).json(user);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.get('/user', async (req, res) => {
  try {
    const users = await User.find({});
    res.status(200).json(users);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
