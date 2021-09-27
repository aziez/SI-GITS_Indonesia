const config = require('../config/database');
const mysql = require('mysql');
const pool = mysql.createPool(config);

pool.on('error', (err) => {
    console.error(err);
});

module.exports = {

    getUser(req, res){
        pool.getConnection(function(err, connection){
            if(err) throw err;
            connection.query(`SELECT * FROM user;`, function(error, result){
                if(error) throw error;

                res.send({
                    sucsess: true,
                    message: 'Berhasil Mengambil Data',
                    data: result
                });
                
            });

            connection.release();
        })
    },

    getUserById(req,res){
        let id = req.params.id;

        pool.getConnection(function(err, connection){
            if(err) throw err;

            connection.query(`SELECT * FROM user WHERE id = ?;`, [id],
            function(error, result){
                if(error) throw error;
                res.send({
                    sucsess: true,
                    message: 'Berhasil Mengambil Data',
                    data: result
                });
            });

            connection.release()
        });
    },

    addUser(req,res){
        let data = {
            usernameData : req.body.username,
            emailData : req.body.email,
            passwordData : req.body.password
        }

        pool.getConnection(function(err, connection){
            if(err) throw err;

            connection.query(`INSERT INTO user SET ?;`, [data],
            function(error, result){
                if(error) throw error;
                res.send({
                    sucsess: true,
                    message: 'Berhasil Menambah Data'
                });
            });

            connection.release();
        })
    },

    editUser(req,res){
        let dataEdit = {
            usernameData : req.body.username,
            emailData : req.body.email,
            passwordData : req.body.password
        }

        let id = req.body.id;

        pool.getConnection(function(err, connection){
            if(err) throw err;
            connection.query(`UPDATE user SET ? WHERE id = ?;`, [dataEdit, id],
            function(eror, result){
                if(error) throw error;
                res.send({
                    sucsess: true,
                    message: 'Data Berhasil Di Edit',
                });
            });

            connection.release();
        })
    },

    deleteUser(req,res){
        let id = req.body.id;

        pool.getConnection(function(err, connection){
            if(err) throw err;
            connection.query(`DELETE FROM user WHERE id = ?;`,[id],
            function(error, result){
                if(error) throw error;

                res.send({
                    sucsess: true,
                    message: 'Berhasil Menghapus Data'
                });
            });
            connection.release()
        })
    }
}