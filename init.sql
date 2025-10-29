CREATE DATABASE boilerplate;

-- Create People table
CREATE TABLE boilerplate.people (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL
);

-- Create Widgets table
CREATE TABLE boilerplate.widgets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255),
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES boilerplate.people(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_widgets_owner_id ON boilerplate.widgets(owner_id);
CREATE INDEX idx_people_name ON boilerplate.people(name);
CREATE INDEX idx_widgets_name ON boilerplate.widgets(name);
