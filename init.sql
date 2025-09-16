-- Create People table
CREATE TABLE people (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL
);

-- Create Widgets table
CREATE TABLE widgets (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value VARCHAR(255),
    owner_id BIGINT REFERENCES people(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_widgets_owner_id ON widgets(owner_id);
CREATE INDEX idx_people_name ON people(name);
CREATE INDEX idx_widgets_name ON widgets(name);
