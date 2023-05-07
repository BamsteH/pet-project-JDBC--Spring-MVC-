package com.example.demo.notification.repository;

import com.example.demo.application.repository.CrudRepository;
import com.example.demo.notification.entity.Notification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Optional;

@Repository
public class NotificationRepository implements CrudRepository<Notification, Long> {

  private final JdbcTemplate template;

  public NotificationRepository(JdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Notification create(Notification notification) {
    String sql = "INSERT INTO Notifications(message, payload) VALUES (?,?)";
    template.update(
            sql,
            notification.getMessage(),
            notification.getPayload().value()
    );
    sql = "SELECT * FROM Notifications WHERE id=LAST_INSERT_ID()";
    return template.queryForObject(sql,
            (resultSet, rowNum) -> new Notification(
                    resultSet.getLong("id"),
                    resultSet.getString("message"),
                    new Json(resultSet.getString("payload"))
            )
    );

  }

  @Override
  public Optional<Notification> getById(Long id) {
    String sql = "SELECT * FROM Notifications WHERE id=?";
    try {
      return Optional.ofNullable(
              template.queryForObject(
                      sql,
                      (resultSet, rowNum) -> new Notification(
                              resultSet.getLong("id"),
                              resultSet.getString("message"),
                              new Json(resultSet.getString("payload"))
                      ),
                      id
              )
      );
    } catch (Exception exception) {
      return Optional.empty();
    }
  }

  @Override
  public List<Notification> getAll() {
    return null;
  }

  @Override
  public List<Notification> getAll(int offset, int limit) {
    return null;
  }

  @Override
  public Optional<Notification> update(Notification notification, Long id) {
    return Optional.empty();
  }

  @Override
  public boolean delete(Long id) {
    String sql = "DELETE FROM Notifications WHERE id=?";
    int result = template.update(sql, id);
    return result > 0;
  }

}
