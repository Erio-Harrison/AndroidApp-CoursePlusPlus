package com.example.couseplusplus.data.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class UserDatasourceTest {

  @Test
  public void testGetInstance() {
    UserDatasource uut = UserDatasource.getInstance();
    assertEquals(UserDatasource.getInstance(), uut);
  }
}
