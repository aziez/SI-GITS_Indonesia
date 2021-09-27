const config = require('../config/database');
const mysql = require('mysql');
const pool = mysql.createPool(config);

pool.on('error', (err) => {
    console.error(err);
});

module.exports = {

    getTodo(req, res){
        pool.getConnection(function(err, connection){
            if(err) throw err;
            connection.query(`SELECT * FROM task;`, function(error, result){
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

    getTodoById(req,res){
        let id = req.params.id;

        pool.getConnection(function(err, connection){
            if(err) throw err;

            connection.query(`SELECT * FROM task WHERE id = ?;`, [id],
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

    addTodo(req,res){
        let data = {
            taskName : req.body.task,
            status : req.body.stat,
        }

        pool.getConnection(function(err, connection){
            if(err) throw err;

            connection.query(`INSERT INTO task SET ?;`, [data],
            function(error, result){
                if(error) throw error;
                res.send({
                    sucsess: true,
                    message: 'Berhasil Menambah Data',
                    data: result
                });
            });

            connection.release();
        })
    },

    editTodo(req,res){
        let dataEdit = {
            taskName : req.body.task,
            status : req.body.stat,
        }

        let id = req.body.id;

        pool.getConnection(function(err, connection){
            if(err) throw err;
            connection.query(`UPDATE task SET ? WHERE id = ?;`, [dataEdit, id],
            function(error, result){
                if(error) throw error;
                res.send({
                    sucsess: true,
                    message: 'Data Berhasil Di Edit',
                });
            });

            connection.release();
        })
    },

    deleteTodo(req,res){
        let id = req.body.id;

        pool.getConnection(function(err, connection){
            if(err) throw err;
            connection.query(`DELETE FROM task WHERE id = ?;`,[id],
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