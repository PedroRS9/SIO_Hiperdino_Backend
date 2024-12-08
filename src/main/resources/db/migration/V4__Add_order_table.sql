CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    warehouse_id INTEGER REFERENCES warehouse(id),
    store_id INTEGER REFERENCES store(id),
    product_id INTEGER REFERENCES product(id),
    quantity INTEGER,
    delivered BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO warehouse (name, location) VALUES
    ('HiperDino Plataforma Logística Telde', 'Parque Tecnológico Lomos de Silva Parc.1, 35214 Telde, Las Palmas'),
    ('HiperDino Plataforma Logística Güímar', 'Polígono Industrial Valle de Güímar Manzana XII - Parcela I, 38550 Arafo, Santa Cruz de Tenerife');