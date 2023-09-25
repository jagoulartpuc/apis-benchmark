const mongoose = require('mongoose');
const User = require('./user');

import server from "bunrest";
const port = process.env.PORT || 3001;
const mongoURI = 'mongodb://localhost:27017/coredb?retryWrites=true'; // Replace with your MongoDB URI

mongoose.connect(mongoURI, { useNewUrlParser: true, useUnifiedTopology: true })
    .then(() => {
        console.log('Connected to MongoDB');
    })
    .catch((error: any) => {
        console.error('MongoDB connection error:', error);
    });

const app = server();

app.post('/user', async (req, res) => {
    const user = new User(req.body);
    const savedUser = await user.save();
    res.status(201).json(savedUser);
});

app.get('/user', async (req, res) => {
    const users = await User.find({});
    res.status(200).json(users);
});

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
